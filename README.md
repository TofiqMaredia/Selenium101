
# Selenium 101 Assignemnt

### Prerequisites
Shared this private Repository to "LambdaTest-Certifications"

### Run your First Test
1. Open up the Gitpod environment from Github Repository (Added the .gitpod.yml file to the Repo.)

   This will install the maven dependencies and build the complete project and will trigger build failure. This is because of  WebDriver Unauthorized, either Username or AccessKey.

2. Set Lamabdatest Credentials from the Gitpod VScode terminal. You can get these from lambdatest automation dashboard.

```
export LT_USERNAME="YOUR_USERNAME"
export LT_ACCESS_KEY="YOUR ACCESS KEY"
```

3. Run tests
### Run Single Test
Use the command below from the root of the project to run single tests.
```
mvn test -D suite=single.xml
```
### Run Parallel Test
Use the command below from the root of the project to run parallel tests.
```
mvn test -D suite=parallel.xml
```

### Assignment Tasks
1. Test Scenario 1 - Completed
2. Test Scenario 2 - Completed
3. Test Scenario 3 - Completed (Cannot validate step 3. This is because on clicking submit "Please fill in the fields" error message is not displayed on the page.)

###  Results - Test Scenario's Parallel Execution Status on Lambda Automation Cloud Platform
```
mvn test -D suite=parallel.xml
```

1. Test Scenario 2 (basicTest) - Test Id - W7KVP-9ID2Q-U4N4Y-ZZTK5
2. Test Scenario 3 (basicTest) - Test Id - 9WOUK-AWLNM-OLUQ6-O3FFP
3. Test Scenario 1 (basicTest) - Test Id - 0PEB5-LSEDV-QNZRI-3MHTB
