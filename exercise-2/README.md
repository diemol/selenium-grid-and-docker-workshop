# First exercise

What we will do:

* Start a Dynamic Selenium Grid with Docker
* Explore the Grid and run a few tests

### Start Selenium Grid with Docker

1. Under the `exercise-2` directory, check the `docker-compose-v3-dynamic-grid.yml` file. 
    * Set `SE_NODE_GRID_URL` to `localhost` if you are using your own machine
    * If you are using GitPod, use your GitPod url (e.g. https://4444-orange-dragon-cpm1xit2.ws-eu17.gitpod.io).
2. If using GitPod:
    * In the `config.toml` file, replace `url` with the result of `hostname -I` and port `2375`.
    * E.g. `url = "http://10.76.10.28:2375"`
3. On a terminal, under the `exercise-2` directory, type `docker-compose -f docker-compose-v3-dynamic-grid.yml up`.
4. Head to your machine/instance url, and check the port `4444`

### Explore the Grid

1. While being on the Grid site, explore the different Nodes, available browsers, and tabs.

### Run a few tests

1. Explore the tests located under the `exercise-2/tests-exercise-2`, specifically the `SeleniumGridTestExercise2` class.
2. Open a new terminal, and under the `exercise-2/tests-exercise-2`, run `mvn clean test`.
3. Head to the Grid UI, and see how tests get executed.
4. When you are done, under the `exercise-2` directory, type `Ctrl+C` to stop the Grid and then `docker-compose -f docker-compose-v3-dynamic-grid.yml down`
5. Explore the `assets` directory, check the capabilities file and the video.
