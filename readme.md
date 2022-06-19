// download the code
// open in intellij
//Run UrlShoternerApplication

// Run redis-server
// Use POSTMan to 

POST request:
http://localhost:8340/rest/url
body: {
"url": "https://www.codeprimers.com/spring-boot-url-shortener"
}

GET request:
http://localhost:8340/rest/url/{key obtained from POST request}