package be.swsb.fiazard.ordering.resource;

import be.swsb.fiazard.common.Id;
import be.swsb.fiazard.common.error.ErrorR;
import be.swsb.fiazard.ordering.representation.product.ProductR;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = ProductResourceV1.PRODUCTS_BASE_URI, description = "Operations about Products")
@Path(ProductResourceV1.PRODUCTS_BASE_URI)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResourceV1 {

    public static final String PRODUCTS_BASE_URI = "/v1/products";
    private static final List<ProductR> allProducts;

    static {
        ProductR clubCheese = new ProductR.ProductRBuilder()
                .withCategoryId(Id.fromString(CategoryResourceV1.cheese.getId()))
                .withName("Club Cheese")
                .withComposition("gouda cheese", "salad", "tomatoes", "egg")
                .withSauces("mayonaise")
                .build();
        ProductR breeze = new ProductR.ProductRBuilder()
                .withCategoryId(Id.fromString(CategoryResourceV1.cheese.getId()))
                .withName("Breeze")
                .withComposition("brie", "walnuts")
                .withSauces("honey")
                .build();
        ProductR clubHam = new ProductR.ProductRBuilder()
                .withCategoryId(Id.fromString(CategoryResourceV1.ham.getId()))
                .withName("Club Ham")
                .withComposition("ham", "salad", "tomatoes", "egg")
                .withSauces("mayonaise")
                .build();
        ProductR clubParmaham = new ProductR.ProductRBuilder()
                .withCategoryId(Id.fromString(CategoryResourceV1.ham.getId()))
                .withName("Club Ham")
                .withComposition("parma ham", "salad", "tomatoes", "egg")
                .withSauces("mayonaise")
                .build();
        allProducts = Lists.newArrayList(clubHam, clubCheese, breeze, clubParmaham);
    }

    @GET
    @Timed
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = ProductR[].class, message = ""),
            @ApiResponse(code = 403, response = ErrorR.class, message = "Unauthorized")
    })
    public Response getByCategoryId(@QueryParam(value = "categoryId") String categoryId) {
        if (Strings.isNullOrEmpty(categoryId)) {
            return Response.ok(
                    allProducts,
                    MediaType.APPLICATION_JSON_TYPE)
                    .build();
        }

        List<ProductR> filteredProducts = allProducts.stream()
                .filter(p -> Id.fromString(p.getCategoryId()).equals(Id.fromString(categoryId)))
                .collect(Collectors.toList());
        return Response.ok(
                filteredProducts,
                MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
