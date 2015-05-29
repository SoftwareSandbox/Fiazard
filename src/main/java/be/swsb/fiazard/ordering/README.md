#Ordering Module

##Goal

This module should be excellent at allowing one to order a sandwich.

##Dependencies

###Incoming
The *Invoicing Module* is going to need this module to get a bunch of orders to total an Invoice.

###Outgoing
It indirectly requires there to be a list of sandwiches (in the future maybe only ingredients) and categorization of those sandwiches.
To achieve this dependency, we're just "re-using" the read model of the *Managing Module* for now by querying its collections (Products and stuff).

##Decisions
We are postponing a mapping layer between the representations and commands because it's simpler. We are aware of the impact that versioning representations might have.

