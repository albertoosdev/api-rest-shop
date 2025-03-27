

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/albertoosdev/api-rest-shop">
    <img src="https://i.imgur.com/xiT8jEg.png" alt="Logo" width="130" height="100">
  </a>

<h3 align="center">API Rest MyShop</h3>

  <p align="center">
    API Rest managing products
    <br />
    <a href="https://github.com/albertoosdev/api-rest-shop"><strong>Explore the docs »</strong></a>
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
      </ul>
    </li>
    <li><a href="#installation">Installation & usage</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This is a project in which through a Rest API in which we can manage products and prices through the following calls:
  

![][GetByParam-example]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

This section should list any major frameworks/libraries used to bootstrap your project.

* [![Java][Java-logo]][Java-url]
* [![Spring][Spring-logo]][Spring-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* Install [Java][Java-url]
* Install [Maven][Maven-install-url]

## Installation & Usage

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1.  Clone the repo
   ```sh
   git clone https://github.com/albertoosdev/api-rest-shop.git
   ```
2. Install Maven dependencies
   ```sh
   mvn install
   ```
3. Execute the project
   ```sh
   mvn spring-boot:run
   ```
   or
   ```sh
   java -jar {project_path}/target/shop-0.0.1-SNAPSHOT.jar
   ```
4. Send API Request
   ```sh
   curl --location 'http://localhost:8080/price?applicationDate={applicationDate}&productId={productId}&brandId={brandId}' --header 'Accept: application/json'
   ```
   Example:
   ```sh
   curl --location 'http://localhost:8080/price?applicationDate=2020-06-14T10%3A00%3A00Z&productId=35455&brandId=1' --header 'Accept: application/json'
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[Spring-logo]: https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white
[Spring-url]: https://spring.io/
[Java-logo]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/es/
[Maven-install-url]: https://maven.apache.org/install.html
[GetByParam-example]: https://i.imgur.com/hXGHmpd.png