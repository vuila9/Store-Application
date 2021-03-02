# Store-Application JAVA Graphical User Interface
This is a small project in COMP1406-W20

Please do not copy this code as I do not permit it :)
These codes are for viewed only

This project requires JavaFX so if you dont have it set up, downloading this codes and run would simply dont work.

Here is step by steps to have it set up before you can run this.

First and foremost, I am using IntelliJ IDEA as my main platform for java, I have not tried other platform like Eclipse or Microsoft VC
so this instruction might not work for them.
The OS I am using is Windows 10 Pro.

So, let us begin:

Again, I am using IntelliJ IDEA as my platform, free version works just fine. Be sure to update it to the latest version.

- First of all, go to this website: https://gluonhq.com/products/javafx/, and download the latest version of Java SDK (as of this time, it's 15.0.1)
- Extract the zip file, you will have a folder named like this: "javafx-sdk-15.0.1"
  + Move the file to a location that is convenient for you, in my case, I put it directly inside where I install my IntelliJ: C:\Program Files\JetBrains\
    ![image](https://user-images.githubusercontent.com/62405278/109594455-d2a19580-7ae0-11eb-80bc-d987b2cc1b8c.png)
- Open IntelliJ, click on File, go to Setting, click on "Path Variables", click on the little "+" on botton left corner to add a path
  + You should name it as: PATH_TO_FX (or whatever name that is clear, because you will use it later)  
  + After that, in the "value" section, you will browse to the folder you move previously "javafx-sdk-15.0.", you open it and select the folder called "lib"
  + You should have something like this: 
    ![image](https://user-images.githubusercontent.com/62405278/109595017-b7835580-7ae1-11eb-9482-420d953b80c6.png)
 
- Now, you will copy all the source files in my repo, notice if you are organizing it into a package, be sure to include the line "package name" on top of every source files.
- At this point, you should try to run the program, it will gave some errors, just ignore it.
- Then, you will locate the at this position, click on it and select "Edit Configuration" 
  ![image](https://user-images.githubusercontent.com/62405278/109594796-67a48e80-7ae1-11eb-811d-167ea7ff266a.png)
  
- Click on "Modify options", be sure to select the "Add VM options" 
 
  ![image](https://user-images.githubusercontent.com/62405278/109595135-ed283e80-7ae1-11eb-8413-2c94090f2765.png)
  
- In the "VM options" box, click on the expand arrows: 

  ![image](https://user-images.githubusercontent.com/62405278/109595209-1052ee00-7ae2-11eb-925c-88a435a1b74f.png)
  
- Type exactly like this image: 
  + Use the name of the path you gave previously for the line ${your_Path_name}
  + For simplicity sake, please don't press 'enter' when you finish typing those texts, just click on the collapse arrows when you done
  ![image](https://user-images.githubusercontent.com/62405278/109595367-5f008800-7ae2-11eb-9f70-68477b1920e6.png)

You are a third-quarter way done, now we will do the last set up
- Click on File, go to Project Structure (or simply type Ctrl + Alt + Shift + S)
- Go to "Libraries" tab, click on the "+", click on "Java", then browse to the folder "javafx-sdk-15.0.1" that you install at the beginning, open the folder and select "lib"  then press Ok.
  + You should have something like this:
 
    ![image](https://user-images.githubusercontent.com/62405278/109596267-e4386c80-7ae3-11eb-80c0-647e3d501447.png)
    
- Then, go to "Project" tab on that same window, if your version is up-to-date then there's nothing else needs to be done, just for reference, here is what mine looks like:

  ![image](https://user-images.githubusercontent.com/62405278/109596717-cae3f000-7ae4-11eb-8855-3cf80424a807.png)
  
- Then, go to "SDKs" just under the "Platform Settings", you should have the number of version folder of the SDK you installed at the begining (i.e version 15.0.1 will be 15, version 16.xx.xx will be 16)
  + You can ignore the open-jdk-15
  
  ![image](https://user-images.githubusercontent.com/62405278/109596863-0da5c800-7ae5-11eb-80fc-c98f0b20d611.png)

  And that is it!



