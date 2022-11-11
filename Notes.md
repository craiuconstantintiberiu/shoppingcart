Time spent: around 2 hours (disregard commit times, took dogs out for a walk for around 30 minutes during this).

Regarding the printing of the Shopping Cart, I created a separate interface that deals with printing a list of shopping items. This was done because I wanted to decouple as much as possible the actual shopping cart from the receipt generation. 
I created two separate classes for printing the receipt as-is or with the price as the first column. I could have created a separate system for formatting the receipt, but it felt too brittle and I thought it would grow hard to use. E.g: What if we want to present the customer with a personalized message, or add some more columns or some such. It felt like it would be too much of a  headache to account for possible changes and it would also be a huge headache for the people who might want to extend this in the future.

Regarding the tests, I vastly extended the test suite coverage. I also used mocking for better unit tests.

The receipt prints items in the order in which they were scanned: the ShoppingCart class is backed by a LinkedHashMap.

I created a POJO to represent an item with a name, price and quantity, to be used when generating a receipt.

Would have liked to use a BigDecimal or similar instead of using integers and floats for the prices.
I assumed that in a legacy environment this would have been done for a reason, so I did not touch this, except to create an interface for the Pricer and a in-memory variant(e.g: I assumed the Pricer is a database).

Tried to adhere to using interfaces to make the code as open to extensions as possible and depend on abstractions, not concrete classes.

Fun experience, would repeat.
