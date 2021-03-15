# Android AI App to predict the strength of the concrete
***An innovative AI-tool to predict the strength of the concrete***

## Table Of Contents
* App Link
* Business Problem
* Machine Learning based solution
* User-End: Android App
* Installation
* Languages Used
* Contact Me
* License
* Credits


## App Link
[App Link](https://www.linkedin.com/feed/update/urn:li:activity:6777141197023670272/)


## Business Problem
Concrete is considered by many to be a strong and durable material, and rightfully so. Compressive strength of concrete is the most common and well-accepted measurement of concrete strength to assess the performance of a given concrete mixture. It measures the ability of concrete to withstand loads that will decrease the size of the concrete. Compressive strength is important as it is the main criteria used to determine whether a given concrete mixture will meet the needs of a specific job.

In this project, we are going to see how to use data science and machine learning technologies to predict the strength of the concrete beforehand. By doing this, the engineer knows whether is this mixture is strong enough to meet their demands or not.


## Machine Learning based solution
 * **First Step:** Collect the concrete strength dataset from kaggle.
 * **Second Step:** Analyzed the data by checking its data quality: missing values, outliers, data format, etc. After this, we visualized the dataset to extract insights about the data.
 * **Third Step:** After checking the data quality and visualizing the data set, we have partitioned the dataset into two subsets called ***X_train & y_train*** and ***X_test & y_test***.
 * **Fourth Step:** We have transformed the two subsets using a MinMaxScaler to scale the values between 0 to 1.   
 * **Fifth Step:** Trained 6 Artificial Neural Network Models using 6 different optimizers and saved the model with highest r2-score i.e **the model with Adam optimizer**.
 
 * **Sixth Step:** After saving the Keras model (in .h5 format), the keras model is then converted to a tflite model (in .tflite format).
 
## User-End: Android App
 * **First Step:** Set-up the edittext, textbox and button.
 * **Second Step:** Created two arrays named min and max. This array is used in the same way as the MinMaxScaler() from python.
 * **Third Step:** Created two functions/methods to load the 1) tflite model and 2) Use the loaded model to make predictions.
 * **Fourth Step:** Scaled down the values from the edittext boxes (refer step 2)  using the formula **(X - Xmin) / (Xmax - Xmin)** and sent it to the model to predict.
 * **Fifth Step:** Displayed the output to the users.


## Installation
The application is written in Python 3.7. If you don't have Python installed you can find it [here](https://www.python.org/). If you are using a lower version of Python you can upgrade using the pip package, ensuring you have the latest version of pip. To install the required packages and libraries, run this command in the project directory after cloning the repository :-

```
pip install -r requirements.txt
```

## Languages Used
* [Python](https://www.python.org/)
  * [Spyder](https://www.spyder-ide.org/)
  * [Jupyter Notebook](https://jupyter.org/)
  
* [Android Studio](https://developer.android.com/studio)


## Contact Me
* [Soorya's LinkedIn](https://www.linkedin.com/in/sooryaprakashparthiban/)
* [Soorya's Twitter](https://twitter.com/Drdataspp)


## License
**Copyright 2021 Soorya Prakash Parthiban**

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.


## Credits
Thanks to kaggle and dataset provider for providing this dataset.

