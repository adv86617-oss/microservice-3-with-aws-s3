Atul Verma
Property Service Microservice – Business Flow (Spring Boot + S3)

---

## 1. User / Client Action

* User (Admin / Property Owner) wants to add a new property with rooms and images.
* Opens Web/Mobile UI or uses Postman.
* Fills property details (name, city, area, state, rooms, etc.) and uploads images.

---

## 2. Request Sent

* HTTP POST request to `/api/v1/property/add-property`
* Content-Type: `multipart/form-data`
* Two parts:

  1. `property` → JSON of property details (`PropertyDto`)
  2. `files` → property images

---

## 3. Controller Layer (PropertyController)

* Receives the request.
* Converts JSON string to `PropertyDto` using ObjectMapper.
* Sends `PropertyDto` + images to PropertyService.
* Returns APIResponse back to the client.

---

## 4. Service Layer (PropertyService)

* **Validation / Business Logic:**

  * Check if city, area, state exist (fetch from repositories).
  * Create Property entity and save to Property table.
* **Rooms Handling:**

  * Loop over RoomsDto list.
  * Create Rooms entity for each room.
  * Save to Rooms table.
* **Image Handling:**

  * Call S3Service to upload images.
  * Receive URLs and save to PropertyPhotos table.

---

## 5. AWS S3 Layer (S3Service)

* Uploads images to S3 bucket.
* Generates unique filenames using UUID.
* Returns public URLs to Service layer.

---

## 6. Database Layer

* Tables involved:

  * Property → main property info
  * Rooms → details of each room
  * PropertyPhotos → image URLs
* S3 stores actual images; DB stores metadata.

---

## 7. Response

* PropertyService returns Property entity.
* Controller wraps in APIResponse.
* Client receives JSON with:

  * Property details
  * Rooms list
  * Uploaded image URLs
  * HTTP status 201 → Created

---

## 8. Key Business Points

* **Separation of Concerns:** Controller → Service → S3 → DB
* **Scalable:** Images on S3, DB only metadata.
* **Safe & Unique:** UUID filenames prevent collision.
* **Audit & Traceability:** Property, Rooms, Photos linked with foreign keys

---

**Flow Summary Diagram:**

Client (UI/Postman)
|
v
Controller → PropertyController
|
v
Service → PropertyService
/&#x20;
/  &#x20;
DB (Property, Rooms, PropertyPhotos)   S3 (Images)
|
v
Response → APIResponse → Client
