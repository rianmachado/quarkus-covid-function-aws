#!/bin/bash

echo '###########################################################################################'
echo '#                                                                                         #'
echo '#                                  PUBLISH AWS FUNCION (RIAN)                             #'
echo '#                                                                                         #'
echo '###########################################################################################'


FUNCTION_NAME=QuarkusCovidFunctionAws
HANDLER=io.quarkus.amazon.lambda.runtime.QuarkusStreamHandler::handleRequest
RUNTIME=java11
ZIP_FILE=fileb:///Users/rian/GFT/Projetos/ARSENAL/quarkus-covid-function-aws/target/function.zip
LAMBDA_ROLE_ARN=arn:aws:iam::585849681126:role/lambda-role

  
echo Creating function
  set -x
  aws lambda create-function \
    --function-name ${FUNCTION_NAME} \
    --zip-file ${ZIP_FILE} \
    --handler ${HANDLER} \
    --runtime ${RUNTIME} \
    --role ${LAMBDA_ROLE_ARN} \
    --timeout 15 \
    --memory-size 512
    
# Enable and move this param above ${LAMBDA_META}, if using AWS X-Ray
#    --tracing-config Mode=Active \
