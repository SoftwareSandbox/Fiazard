db = new Mongo().getDB("fiazard");
/* Products */
db.products.remove({});
db.products.insert({
    "category": {"name": "Cheese", "img": "/images/250px-Sandvich.png"},
    "name": "Club cheese",
    "type": "Club",
    "composition": [
        "gouda cheese",
        "salad",
        "tomatoes",
        "egg"
    ],
    "sauces": [
        "mayonaise"
    ],
    "price": 2.0
});
db.products.insert({
    "category": {"name": "Cheese", "img": "/images/250px-Sandvich.png"},
    "name": "Breeze",
    "type": "Special",
    "composition": [
        "brie",
        "walnuts"
    ],
    "sauces": [
        "honey"
    ],
    "price": 3.5
});
db.products.insert({
    "category": {"name": "Ham", "img": "/images/250px-Sandvich.png"},
    "name": "Club Ham",
    "type": "Club",
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
});
db.products.insert({
    "category": {"name": "Ham", "img": "/images/250px-Sandvich.png"},
    "name": "Club Parma",
    "type": "Club",
    "composition": [
        "parmaham",
        "salad",
        "tomatoes",
        "egg"
    ],
    "sauces": [
        "mayonaise"
    ],
    "price": 3
});

/* Buns */
db.buns.remove({});
db.buns.insert({
    "name": "White",
    "price": 0.20
});
db.buns.insert({
    "name": "Ciabatta",
    "price": 0.35
});
db.buns.insert({
    "name": "Wholegrain",
    "price": 0.35
});
db.buns.insert({
    "name": "Rye",
    "price": 0.20
});

/* Toppings */
db.toppings.remove({});
db.toppings.insert({
    "name": "Gouda",
    "price": 0.20
});
db.toppings.insert({
    "name": "Grilled Ham",
    "price": 0.20
});
db.toppings.insert({
    "name": "Smoked Salmon",
    "price": 0.30
});
db.toppings.insert({
    "name": "Iceberg Salad",
    "price": 0.05
});
db.toppings.insert({
    "name": "Pickles",
    "price": 0.10
});
db.toppings.insert({
    "name": "Tomatoes",
    "price": 0.10
});
db.toppings.insert({
    "name": "Walnuts",
    "price": 0.15
});

/* Condiments */
db.condiments.remove({});
db.condiments.insert({
    "name": "Mayonaise",
    "price": 0.10
});
db.condiments.insert({
    "name": "Ketchup",
    "price": 0.10
});
db.condiments.insert({
    "name": "Honey",
    "price": 0.20
});
db.condiments.insert({
    "name": "Relish",
    "price": 0.20
});