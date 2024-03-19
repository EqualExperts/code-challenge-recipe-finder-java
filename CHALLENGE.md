# Background

Recipes4Success (R4S) is a new business whose mission is to ignite a passion for cooking in young people. We supply our customers with all the information they need to produce delicious fun meals quickly and at a reasonable cost.

A while ago, we commissioned another software consultancy to produce a recipe API for us. The project has not gone well. After many months, we have a working API, but the quality of the software is poor, and the relationship has become unworkable.  We are desperate to add new features to our API, and are hoping to build a good relationship with another consultancy. Hence these discussions with you…

# API Description

The existing API has the following features:
- Filter recipe list by a search term
- List all recipes (when search term is blank)

# Technical Design

The API is built around an existing recipes back-end API which has these features:
List all recipe IDs - with names and descriptions, but no other data
Get recipe details by ID

We do not expect that you will need to modify this API.

We have a simple web app that is used to test and demonstrate the recipe API to consumers. We do not expect that you will need to modify much of the webpages in this app (i.e. don’t spend all your time making the pages look nice).  For the meantime, we are designing new API features in a way that can be accommodated by this app.

# New Feature

We would like to add this feature to our API:
- Filter all recipes by total time

The existing API provides preparation time and cooking time for each recipe. The total time required is the sum of these two times.

The new feature allows the consumer to specify a maximum total time. All recipes with a total time less than or equal to the specified time are returned by the API.

We would like you to add a new control on the web page, where users can enter a time.  Then when they filter, only the relevant recipes should be shown.  This should work in conjunction with the test search filter as well.  For example, if the user enters a time of 30 minutes, then all recipes that can be prepared and cooked within (and including) 30 minutes should be shown.

We would like you to build this feature for us, and we expect a high level of quality. In particular, it should be easy to read and maintain.

# Acceptance Tests
When the completed work is delivered, we would like you to demonstrate the following tests:

For total time of 30, return
list of recipes each of which has a total time of 30 minutes or less

For total time of 500’, return
all the recipes

For a query where total time is set to  60 and a search term of ‘sauce’ will only return recipes that can be cooked and prepared in 60 minutes and have the term sauce in its description or instructions.






