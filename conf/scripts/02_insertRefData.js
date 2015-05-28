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