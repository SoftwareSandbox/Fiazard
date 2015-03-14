db = new Mongo().getDB("local");
/* Products (also contain Categories) */
db.createCollection('products');

/* Openinghours */
db.createCollection('openinghours');