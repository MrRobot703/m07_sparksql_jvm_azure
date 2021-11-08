# Spark Databricks SQL

This a simple databricks SQL application and is available on . First there are some necessary components that
need to be installed and provisioned:

* Azure account
* Azure Databricks service
* Terraform
* Auzer-cli
* Databricks cli (for providing secrets store, available only for premium subscription)

For using azure key vault please save your secrets names into string constants in config.Constants class

## Terraform
To get started just run:
```
terraform init
terraform apply
```
in the terraform folder. Also, if you want to keep .trstate file somewhere private,
I suggest you to check out [terraform official guids](https://www.terraform.io/docs/language/settings/backends/azurerm.html)
for reference. The easiest so far approach is to authenticate via access_key with azure blob storage
and keep state file there.
And don't forget to run:
```
terraform destroy
```
after you finished with application so not to waste resource in the cloud.

## Azure cli and Databricks cli
You can download azure cli with homebrew if you are on mac. For other platforms please refer to
[microsoft guids](https://docs.microsoft.com/en-us/cli/azure/)

```
brew update && brew install azure-cli
az login
```

To download and install databricks cli you need python or pip package manager.
After python installation you can run following commands:

```
pip install databricks-cli
```
For more detailed guid please refer to [databricks docs](https://docs.databricks.com/dev-tools/cli/index.html)

## Start the application
First run the java Driver class to copy files from external azure blob storage into internal one.
Then go into databricks notebook and upload sparksql_jvm_azure.dbc from notebooks folder.

