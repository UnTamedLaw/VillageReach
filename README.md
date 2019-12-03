[![CircleCI](https://circleci.com/gh/UnTamedLaw/VillageReach.svg?style=svg)](https://circleci.com/gh/UnTamedLaw/VillageReach)
[![codecov](https://codecov.io/gh/UnTamedLaw/VillageReach/branch/master/graph/badge.svg)](https://codecov.io/gh/UnTamedLaw/VillageReach)

```Village Reach Application Documentation v.02
```

```Description: A Proof of Delivery application to access and fill proof of delivery offline for OpenLMIS to be completed by a driver and a clinician as a proof of delivery for received medical shipments.
```

```Prerequisites: At minimum application targets Androids 5.1 with storage availability of 6GB.
```

```Dependencies:

Volley version 1.1.1. https://github.com/google/volley 
Gson 2.8.6 https://github.com/google/gson/blob/master/UserGuide.md
```

```Developers: Yamato Onuki, Kevin Law, Anu Slorah, Abby Abraha, Sean Gilliland, Perla Reyes Herrera
```
 

```ACTIVITIES:
	MainActivity
		protected void onCreate()
			Parameters:
				Bundle savedInstanceState
			Purpose:
				binds username, password, submit buttons and progressbar
		public void onClick()
			Parameters:
				View button
			Purpose:
				Triggered when button is pressed. gets username and password Strings from EditText fields, does Login.login() and displays toasts on failure.
	DeliveryActivity
		protected void onCreate()
			Parameters:
				Bundle savedInstanceState 
			Purpose:
				creates the DeliveryActivity and binds the buttons.
		public void showSync()
			Parameters:
				View view (unused)
			Purpose:
				Triggered when the sync button is pressed. Does basically the same thing as Login.login() but with the credentials taken from storage.
	FormActivity
		protected void onCreate()
			Parameters:
				Bundle savedInstanceState
			Purpose:
				Using the podId taken from the previous DeliveryActivity, it gets the ProofOfDelivery HashMap, the Shipment HashMap and the OrderHashMap and gets the PoD, Shipment and Order associated with the PoD. This data is fed into a new ProofOfDeliveryRecyclerAdaptor using parameters. Code to disable the submitButton if the PoD status is "CONFIRMED" goes here as well.
		private Request generateRequest()
			Purpose:
				Uses the old ProofOfDelivery as a template to create a new Request with data inserted from the EditText fields in each LineItem CardView.
		public void onClickRespond(View view)
			Purpose:
				Called when submit button is clicked. calls validateAllFormData() to validate the input in the forms, writes the Request into the file and closes this activity.
```
```CLASSES:
	Auth
		public static void authenticate()
			Parameters:
				Credentials creds
				Context context
				AuthCallback callback
			Purpose:
				Function requests a token from the server and then writes that token into storage with filename "token"
		public static void saveCredsToStorage()
			Parameters:
				Credentials creds
			Purpose:
				Function saves credentials object into storage with the filename "loginCredentials"
	Login
		public static void login()
			Parameters:
				String username
				String password
				Context context
				AuthCallback callback
			Purpose:
				Does this sequence of synchronous network requests: Auth.authenticate -> Sync.sync
	InternalStorageHandler (Note: Is a singleton and to properly invoke it, do InternalStorageHandler.getInstance(null).yourFunction()
		public String readFile()
			Parameters:
				String filename
			Purpose:
				Reads a file from files folder in app data and returns a string. returns string "File Not Read" if no file is found.
		public void deleteRequestMap()
			Purpose:
				Doesn't actually delete requestMap, but makes requestMap file an empty hashMap. This is called after requests were sent out.
		public void writeOrderToFile()
			Parameters:
				Order order
			Purpose:
				Takes an order object and appends it into the existing HashMap stored in orderMap and saves it. If orderMap doesn't exist, it is created.
		public void writeShipmentToFile()
			Parameters:
				Shipment shipment
			Purpose:
				Takes a shipment object and appends it to the existing HashMap stored in shipmentMap and saves it. If shipmentMap doesn't exist, it is created.
		public void writeRequestToFile()
			Parameters:
				Request request
			Purpose:
				Takes a request object and appends it to the existing HashMap stored in requestMap and saves it. If requestMap doesn't exist, it is created.
		public void writeToFile()
			Parameters:
				String dataToBeStored
				String filename
			Purpose:
				will take any String and write it to a file. Mainly used as a helper for the other ToFile() functions, but is also used to store tokens and credentials.
	NetworkingTest (Note: Is a singleton and to properly invoke it, you need to do NetworkingTest.getInstance(null).yourFunction()
		public static void tokenFromServer()
			Parameters:
				Credentials creds
				Context context
				VolleyCallback callback
			Purpose:
				Makes a GET request to /api/oauth/token to request a token from the server using provided credentials and returns the response in a callback.
		public static void dataFromServerString()
			Parameters:
				String token
				String url
				Context context
				StringCallback callback
			Purpose:
				Makes a GET request to given URL and returns the response in a callback.
		public static void putRequest()
			Parameters:
				String token
				String url
				com.lawk.villagereach.Request value
				Context context
				StringCallback callback
			Purpose:
				Makes a PUT request to given URL and returns the result in a callback.
	Sync
		public static void sync()
			Parameters:
				Context context
				SyncCallback callback
			Purpose:
				This function first does sendDrafts() to send out all the requests saved in the storage, and then requests all relevant data from the server. First it gets all the ProofOfDelivery as an array, and then all the Orderables as an array, and then a single request for each ProofOfDelivery for it's associated Order and Shipment objects. It uses a countDownLatch to wait until all these asynchronous requests have completed before executing the callback.
```	
```DATA OBJECTS:
	Credentials
		Stores username and password.
	Facility
		represents Facility in data.
	FormActivityLineItemEditable
		Used by ProofOfDeliveryRecyclerAdaptor to hold form data and all the lineItem variables that need to be used in one place.
	LineItem
		represents LineItem in data.
	Order
		represents Order in data.
	Orderable
		represents Orderable in data.
	OrderableContent
		container for an array of Orderables. Exists because Gson needs it to parse arrays.
	ProcessingPeriod
		represents ProcessingPeriod in data.
	Program
		represents Program in data.
	ProofOfDelivery
		represents ProofOfDelivery in data.
	ProofsOfDeliveryContent
		container for an array of PoDs. Exists because Gson needs it to parse arrays.
	Request
		A ProofOfDelivery with all required fields to be a valid request for the PUT request in the server. 
	Shipment
		represents Shipment in data.
	Stub
		represents any data with just an Id and a Href
```
```ISSUES:
	OrderRecyclerAdapter is spelled wrong
	NetworkingTest is a dumb name for a class.
	AuthCallback, StringCallback, SyncCallback and VolleyCallback can all be streamlined into a more generic callback type.
	You can go back to the mainActivity login screen from the DeliveryActivity. This should be disabled.
	There is no encryption for credentials or data. Password is stored in paintext for now. 
	Syncing via sync button does not disable any inputs from user. 
	Context is passed into a few functions that don't actually need it, like in NetworkingTest
	Exception handling needs work in networking, sync and login functions.
	Submitted PoDs don't show up as gray in the DeliveryActivity until it's synced.
	There should be a more elegant way to handle waiting for all async calls to complete than a CountdownLatch.
	Input validation for all fields should be more thorough. 
	InternalStorageHandler is very primitive and only deals with Strings. A lot of code could be moved there.
	Gson gets instantiated in a bunch of different places instead of just being a singleton.
	```

