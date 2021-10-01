# Notification System - Subscriber 1 (Consumer)

## Functionalities

Client of the Notification system will be responsible to:

1. Subscribe to a publisher's topic while providing its notification url.

2. Retrieve notification messages published to the topic.

## How it works

__In order to achieve the functionalities of the Publisher, this following approach was adopted in the implementation.__

__1. Publisher exposes an api endpoint that will be used by clients for subscription. A client will subscribe to a topic (which is a category of service) providing its url which will receive notifications sent out by the publisher to the topic.__

	Subscription url of the publisher is:
	
	http://localhost:8000/subscribe/topic1

	Sample deployed clients' urls used to test with the publisher are: 
	
	a. http://localhost:9000/test1
	b. http://localhost:9001/test2
	
	Example of request body:
	{
	  "url": "http://localhost:9000/test1"
	}

	Sample topic is: topic1

__2. The client invokes the notification url.__

	Notification url is:
	
	http://localhost:9000/test1

## Requirements

The following are required to run this project:

1. Java 8 runtime
2. Maven

## How to run

1. Clone the repository

2. Run `cd notification-consumer-one`

3. Start the client

4. If on linux system, run `./mvnw spring-boot:run`

5. If on window system, run `mvnw.cmd spring-boot:run`

The client's consumer will be running on port 9000.

## Test

_1. Run the following command to subscribe a client's url_

`curl -X POST -H "Content-Type: application/json" -H "Accept: application/json" -d '{"url": "http://localhost:9000/test1"}' http://localhost:8000/subscribe/topic1`

Expected response

`{"topic":"topic1","url":"http://localhost:9000/test1"}`


_2. Run run the following command to retrieve notification message_

`curl -X GET -H "Content-Type: application/json" http://localhost:9000/test1`

Expected response

`{"data":{"greeting":"Hello there!"},"topic":"topic1"}`
