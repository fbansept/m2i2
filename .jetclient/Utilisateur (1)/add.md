```toml
name = 'add'
method = 'POST'
url = 'http://localhost:8080/api/produit'
sortWeight = 3000000
id = '64c7213e-92aa-4210-b4f3-2c5bbdf8f50c'

[body]
type = 'JSON'
raw = '''
{
  "email" : "g@g.academxxxxxy",
  "password" : "toto",
  "role": {
    "id" : 3,
    "nom" : "toto"
  }
}'''
```
