# MongoDB data-modeling adventure

## Attempt 1 - Resource == Collection
Because we've set up our `@Resource`'s at `/v1/categories` and `/v1/products`, it only seemed logical that we'd have a Categories and Products collection.

We'd want to have references to a category because when a category changes name, we want to just update the category and have all our products' category name update along with that update.

This, however, is the relational way of thinking. Which is an enabler of anemic domain models, where you have one model for both write and read operations.

Let's question its validity!

## Attempt 2 - Just Products
It just so happens that there's a frickin' [http://docs.mongodb.org/ecosystem/use-cases/product-catalog/](use-case for a Product Catalog) on the MongoDB documentation site.

There's also a [http://docs.mongodb.org/ecosystem/use-cases/category-hierarchy/](Category Hierarchy) use-case on that same website.

After reading both cases, I decided to go with one table: `Products` which would have a model that looks a bit like this:
```javascript
{
    "category": {"name":"Ham", "img":"/images/250px-Sandvich.png"},
    "type": "Club",
    "name": "Club Ham",
    "composition": [
      "ham",
      "salad",
      "tomatoes",
      "egg"
    ],
    "sauces": [
      "mayonaise"
    ],
    "price": 2.0
}

```

Updating a category's name would mean to find all products with the same name, and then updating those products' category's name.

Publishing all the categories via our `/v1/categories` would mean to fetch all all the category.name property of the products collection. This has the added advantage that categories that don't have any products never exist.