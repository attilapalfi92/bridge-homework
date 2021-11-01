# Bridge Homework

### Prerequisites for building and running
- Java 11
- Docker
- Docker compose

### How to run
1. run `make build` in the project root
2. then run `make compose-up`
3. now you should be able to access the swagger ui at `http://localhost:8080/swagger-ui/index.html`

### How to run tests
1. run `make compose-up-test` which starts only the postgres db
2. run 'make run-tests' which executes the tests
