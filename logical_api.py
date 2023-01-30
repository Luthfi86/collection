from elasticsearch import Elasticsearch

# Connect to the Elasticsearch cluster
es = Elasticsearch([{"host": "localhost", "port": 9200}])

# Index a document
doc = {
    "title": "Elasticsearch with Python",
    "body": "This is an example of using Python with Elasticsearch"
}
res = es.index(index="blog", doc_type="post", body=doc)

# Search for documents
query = {
    "query": {
        "match": {
            "body": "Python Elasticsearch"
        }
    }
}
result = es.search(index="blog", body=query)
print("Documents found:", result['hits']['total']['value'])

# Update a document
update = {
    "doc": {
        "title": "Updating Elasticsearch with Python"
    }
}
res = es.update(index="blog", doc_type="post", id=res['_id'], body=update)

# Delete a document
res = es.delete(index="blog", doc_type="post", id=res['_id'])
