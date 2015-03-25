db = new Mongo().getDB("fiazard");
/* Products (also contain Categories) */
db.createCollection('products');

/* Openinghours */
db.createCollection('openinghours');