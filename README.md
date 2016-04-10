# openpath
Project is created with NetBeans 8.1 and JavaSE 8

Dependencies:
	Selenium - http://www.seleniumhq.org/download/
	Chrome Web Driver - https://sites.google.com/a/chromium.org/chromedriver/

To build download create a directory call lib under openpath, copy Selenium for Java libs into it. 
NetBeans will complain about missing libraries from the project. I've not checked the libs into the repo (not sure about the distribution licence). Fixed the missing library references in NetBeans by adding the Selenium JARS to the project.

The openpath application reads a command separated file of username and password; a new line for each pair. A line that start with a # is comment. This line will be skipped. Eg

#username, password
fred,yabadabadoo
barney,hiya

Note: tested with Chrome only
