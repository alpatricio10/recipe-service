# Recipe Service
A demo project for recipe management within the Recime app.

### Endpoints

Currently, the service has two working endpoints:

- #### GET /api/v1/recipe
  This returns all available recipes. It shows the first 10 recipes by default, but it can be configured to show a different set and number of recipes via the *page* and *limit* query parameters. It has no request body.
  
- #### GET /api/v1/recipeByDifficulty?difficulty={difficulty}
  Similarly, this returns a list of available recipes, but the query parameter *difficulty* should be defined to filter the recipes to those of a certain difficulty. Otherwise, it returns an error. The number of recipes can also be configured via the *page* and *limit* query parameters, and it also has no request body.

### Response

#### Successful Response (200 OK)

For both endpoints, the API sends the following JSON response:

```
{
  "total_pages": 1,
  "current_page": 1,
  "data": [
    {
      "id": 1,
      "name": "recipe",
      "description": "description",
      "imageUrl": "http://url.com",
      "difficulty": "EASY",
      "position": 1,
      "createTimestamp": YYYY-MM-dd HH:mm:ss,
      "createdBy": "user",
      "updateTimestamp": YYYY-MM-dd HH:mm:ss,
      "updatedBy": "user"
    },
    ...
  ]
}
```

#### Error Response

For errors, the API sends the following JSON response:

```
{
  "code": "someCode",
  "message": "some error message"
}
```

### How to Run

```shell
# for running the application
$ mvn clean compile
$ mvn spring-boot:run

# for running tests
$ mvn test
```

### Some Random Notes In My Head

- The prescribed error message for having no difficulty only shows when there is a query parameter (*difficulty*) that is equal to a blank. If the query parameter does not exist at all, it is handled as an *Invalid Request Parameters* error. 
- To meet the needs laid out in the specification, I made two API endpoints. I do think having just one common endpoint would suffice for both the filtered and unfiltered GET use cases though.
- There might be a use case for filtering by two difficulty types, but it is not supported here. The endpoint only supposes filtering by one difficulty at a time.
- It is currently set-up with a local H2 database that is initialized with seed data. Since data is structured, I would imagine it to use some other relational database instead though.
- Ideally, there would be some constraints set in the Recipe fields, such as restricting NULL fields or adding unique constraints, but since I don't have a proper DBMS and they aren't relevant (for now!), I did not put them in. I think this is most important for the position property if we don't want any two recipes to have the same position.
- I realized that I totally forgot to add logs and comments within the code, although the whole API (in its current form) should be simple and self-documenting already.
- I miss cooking. I haven't been able to cook anything aside from scrambled eggs in ages.
