import pymongo


client = pymongo.MongoClient("mongodb://localhost:27017/")


db = client["mydatabase"]


collection = db["mycollection"]


doc = {"name": "John Doe", "age": 30}
collection.insert_one(doc)


query = {"age": {"$gt": 25}}
docs = list(collection.find(query))
print("Documents:", docs)

update = {"$set": {"age": 31}}
collection.update_one({"name": "John Doe"}, update)


collection.delete_one({"name": "John Doe"})
