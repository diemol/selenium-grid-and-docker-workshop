# Fourth exercise

What we will do:

* Configure and start Sauce To Go
* Run a few tests
* Check results in Sauce Labs

### Start [Sauce To Go](https://opensource.saucelabs.com/sauce-togo/)

1. If not done before, create a Sauce Labs account (free trial) at https://saucelabs.com/sign-up
2. If using GitPod or Linux:
    * Under the `exercise-4` directory, create the `assets` directory: `mkdir assets`
    * Change the permissions of the directory: `sudo chown 1200:1201 assets`
3. If using GitPod or Linux, on the `exercise-4` directory:
    * In the `config.toml` file, replace `url` with the result of `hostname -I` and port `2375`.
    * E.g. `url = "http://10.76.10.28:2375"`
4. On the `exercise-4` directory, run: 
    * Linux and macOS:
    ```bash
    docker run --rm --name sauce-togo -p 4444:4444 \
    -v ${PWD}/config.toml:/opt/bin/config.toml \
    -v ${PWD}/assets:/opt/selenium/assets \
    saucelabs/stg-standalone:20211124
    ```
    * Windows PowerShell:
    ```powershell
    docker run --rm --name sauce-togo -p 4444:4444 `
    -v ${PWD}/config.toml:/opt/bin/config.toml `
    -v ${PWD}/assets:/opt/selenium/assets `
    saucelabs/stg-standalone:20211124
    ```
5. Head to your machine/instance url, and check the port `4444`

### Explore the Grid

1. While being on the Grid site, explore the different Nodes, available browsers, and tabs.

### Run a few tests

1. Explore the tests located under the `exercise-4/tests-exercise-4`, specifically the `SauceToGoTest` class.
2. If you do not have your Sauce user and accessKey as environment variables, then:
    * Replace `System.getenv("SAUCE_USERNAME")` with your Sauce username.
    * Replace `System.getenv("SAUCE_ACCESS_KEY")` with your Sauce accessKey.
    * You can always get your username and accessKey at https://app.saucelabs.com/user-settings.
3. Open a new terminal, and under the `exercise-4/tests-exercise-4`, run `mvn clean test`.
4. Head to the Grid UI, and see how tests get executed.
5. When you are done, under the `exercise-4` directory, type `Ctrl+C` to stop the Sauce To Go
6. Head to https://accounts.saucelabs.com/ to check the results.

