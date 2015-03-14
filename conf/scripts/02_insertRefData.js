/* Categories */
db.categories.insert({"name":"Cheese", "img":"/images/250px-Sandvich.png"});
db.categories.insert({"name":"Ham", "img":"/images/250px-Sandvich.png"});
db.categories.insert({"name":"Fish", "img":"/images/250px-Sandvich.png"});
db.categories.insert({"name":"Salad", "img":"/images/250px-Sandvich.png"});
db.categories.insert({"name":"Specials", "img":"/images/250px-Sandvich.png"});

var catCheese = db.categories.find({"name":"Cheese"});
var catHam = db.categories.find({"name":"Ham"});
var catFish = db.categories.find({"name":"Fish"});
var catSalad = db.categories.find({"name":"Salad"});
var catSpecials = db.categories.find({"name":"Specials"});

db.products.insert({
    "categoryId": new ObjectId(catDerp),
    "name": "Club cheese",
    "composition": [
      "gouda cheese",
      "salad",
      "tomatoes",
      "egg"
    ],
    "sauces": [
      "mayonaise"
    ],
    "type": "Club",
    "price": 2.0
});
db.products.insert({
    "categoryId": new ObjectId(catDerp),
    "name": "Breeze",
    "composition": [
      "brie",
      "walnuts"
    ],
    "sauces": [
      "honey"
    ],
    "type": "Special",
    "price": 3.5
});
db.products.insert({
    "categoryId": new ObjectId(catDerp),
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
    "type": "Club",
    "price": 2.0
});
db.products.insert({
    "categoryId": new ObjectId(catDerp),
    "name": "Club Parma",
    "composition": [
      "parmaham",
      "salad",
      "tomatoes",
      "egg"
    ],
    "sauces": [
      "mayonaise"
    ],
    "type": "Club",
    "price": 3
});