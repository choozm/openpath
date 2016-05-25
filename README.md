# openpath
Project is created with NetBeans 8.1 and JavaSE 8; compatible with Eclipse. 
Tested with Chrome browser only.

## Dependencies
1. Apache Commons CSV - https://archive.apache.org/dist/commons/csv/binaries/
2. Selenium Standalone Server - http://www.seleniumhq.org/download/
3. Chrome Web Driver - https://sites.google.com/a/chromium.org/chromedriver/

## Building the code 
Create a directory 'lib' under openpath, then copy the JAR files of Selenium Standalone Server and Apache Commons CSV into this directory. 

## Running the code
The openpath application reads a command separated file of username and password; a new line for each pair. A line that start with a # is comment and will be skipped. Example:
```
fred,yabadabadoo
barney,hiya
```
