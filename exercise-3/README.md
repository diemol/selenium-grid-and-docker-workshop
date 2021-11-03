# Third exercise

What we will do:

* Explore the configuration to enable Sauce Labs in Grid
* Start a Dynamic Selenium Grid with Docker
* Explore the Grid and run a few tests
* Check results locally and in Sauce Labs

### Start Selenium Grid with Docker

1. If not done before, create a Sauce Labs account (free trial) at https://saucelabs.com/sign-up
2. Under the `exercise-3` directory, check the `docker-compose-v3-dynamic-sauce.yml` file. 
    * Set `SE_NODE_GRID_URL` to `localhost` if you are using your own machine
    * If you are using GitPod, use your GitPod url (e.g. https://4444-orange-dragon-cpm1xit2.ws-eu17.gitpod.io).
4. If using GitPod or Linux, on the the `exercise-3` directory:
    * In the `config.toml` file, replace `url` with the result of `hostname -I` and port `2375`.
    * E.g. `url = "http://10.76.10.28:2375"`
5. If using GitPod or Linux:
    * Under the `exercise-3` directory, create the `assets` directory: `mkdir assets`
    * Change the permissions of the directory: `sudo chown 1200:1201 assets`
6. On a terminal, under the `exercise-3` directory, type `docker-compose -f docker-compose-v3-dynamic-sauce.yml up`.
7. Head to your machine/instance url, and check the port `4444`

### Explore the Grid

1. While being on the Grid site, explore the different Nodes, available browsers, and tabs.

### Run a few tests

1. Explore the tests located under the `exercise-3/tests-exercise-3`, specifically the `SeleniumGridExercise3Test` class.
2. If you do not have your Sauce user and accessKey as environment variables, then:
    * Replace `System.getenv("SAUCE_USERNAME")` with your Sauce username.
    * Replace `System.getenv("SAUCE_ACCESS_KEY")` with your Sauce accessKey.
    * You can always get your username and accessKey at https://app.saucelabs.com/user-settings.
3. Open a new terminal, and under the `exercise-3/tests-exercise-3`, run `mvn clean test`.
4. Head to the Grid UI, and see how tests get executed.
5. When you are done, under the `exercise-3` directory, type `Ctrl+C` to stop the Grid and then `docker-compose -f docker-compose-v3-dynamic-sauce.yml down`
6. Explore the `assets` directory, check the capabilities file and the video.
7. If using GitPod:
    * Navigate on the terminal to the `exercise-3/assets` directory, then run `python3 -m http.server`
    * Open the suggested url and browse the files.
8. Head to https://accounts.saucelabs.com/ to check the results.

