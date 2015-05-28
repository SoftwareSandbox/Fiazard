db = new Mongo().getDB("fiazard");
testdb = new Mongo().getDB("fiazard-test");

/* Products (also contain Categories) */
db.createCollection('products');
testdb.createCollection('products');

/* Openinghours */
db.createCollection('openinghours');
testdb.createCollection('openinghours');

/* Toppings */
db.createCollection('toppings');
testdb.createCollection('toppings');