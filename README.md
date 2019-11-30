[![CircleCI](https://circleci.com/gh/UnTamedLaw/VillageReach.svg?style=svg)](https://circleci.com/gh/UnTamedLaw/VillageReach)
[![codecov](https://codecov.io/gh/UnTamedLaw/VillageReach/branch/master/graph/badge.svg)](https://codecov.io/gh/UnTamedLaw/VillageReach)

Village Reach Application Documentation v.02

Description:
An application for OpenLMIS to be completed by a driver and a clinician as a proof of delivery for received medical shipments.

Prerequisites:
At minimum application targets Androids 5.1 with storage availability of 6GB.

For Installation:

Libraries Used Volley version 1.1.1. https://github.com/google/volley SoLoader https://github.com/facebook/SoLoader Gson 2.8.6 https://github.com/google/gson/blob/master/UserGuide.md

Classes Auth.java Retrieves user credentials, accesses user token & pushes it to storage Credentials.java User credentials (user name & password) DeliveryActivity.java Retrieves a list of deliveries through a recycler view. Facility.java

FormActivity.java Retrieves proof of delivery

InternalStorageHandler.java Stores tokens, and list of deliveries in a text format in the internal storage of application. It contains a readFile method, a writOrderToFile method, a writeShipmentToFile method, and a writeToFile method. LineItem.java Converts java objects to JSON representation. Login.java Takes user credentials from storage & compares it to the user login for access LoginCredentialActivity.java User logs in & app capture user information & stores it MainActivity.java The entry point of application, where the user logs in and the login procedure is initiated. NetworkingTest.java Checks if connected to the internet and captures token from the server. Order.java Order model for facilities using gson. OrderRecyclerAdapter.java Adapter binds data for UI. Orderable.java Schema of order. OrderableContent.java Converts java objects to JSON representation. ProcessingPeriod.java Converts Java objects to JSON representation for the processing schedule ProofOfDelivery.java Converts Java objects to JSON representation for the proof of delivery form. ProofsOf DeliveryContent.java Shipment.java Converts Java objects to JSON representation for the shipments Sync.java Syncs completed proof of delivery forms to storage Stub.java Converts Java objects to JSON representation.

Coding conventions used: 
Insert Yamato’s diagram

Developers:
Yamato Onuki, Kevin Law, Anu Slorah, Sean Gilliland, Abby Abraha, Perla Reyes Herrera
