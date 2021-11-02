# First exercise

What we will do:

* Start Selenium Grid with Docker
* Explore the Grid
* Run a few tests

### Start Selenium Grid with Docker

1. Under the `exercise-1` directory, check the `docker-compose-v3.yml` file. Replace `SE_NODE_GRID_URL` to `localhost` if you are using your own machine, else use your GitPod url.
2. On a terminal, under the `exercise-1` directory, type `docker-compose -f docker-compose-v3.yml up`. This will start a Grid with a Hub and 3 Nodes (Chrome, Firefox, and Edge).
3. Head to your machine/instance url, and check the port `4444`


### Explore the Grid

1. While being on the Grid site, explore the different Nodes, available browsers, and tabs.

### Run a few tests

1. Explore the tests located under the `exercise-1/tests-exercise-1`, specifically the `SeleniumGridTestExercise1` class.
2. Open a new terminal, and under the `exercise-1/tests-exercise-1`, run `mvn clean test`.
3. Head to the Grid UI, and see how tests get executed.
4. When you are done, under the `exercise-1` directory, type `Ctrl+C` to stop the Grid and then `docker-compose -f docker-compose-v3.yml down`