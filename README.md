# Rewards Management

### Data setup
For maintaining the transactions , have created a sample data with transactions.json under resources folder.
Ensured the data contains the values with in the last 3 months, more than 3 months and also dollars spent with less than 50, more than 100 etc.

### Endpoint
Built an end-point which accepts customerId and past n months as input, fetches the corresponding data, if found valid transactions calculates the reward points by month and returns the response accordingly.
If no transaction found for the customer within the specified past n months, then it returns a  response with status code 400(bad request).
### Swagger URL
http://localhost:8080/swagger-ui/index.html

####/rewards/rewardsSummaryById
######RequestParam:
N month frequency can be passed by pastMonths param,
the reward points by customer id can be queried by passing customerId as pathvariable.

######Sample request:
http://localhost:8080/rewards/rewardsSummaryById/123?pastMonths=3
######Sample response:
[
  {
    "rewardPoints": 90,
    "month": "AUGUST"
  },
  {
    "rewardPoints": 0,
    "month": "SEPTEMBER"
  },
  {
    "rewardPoints": 0,
    "month": "OCTOBER"
  }
]

