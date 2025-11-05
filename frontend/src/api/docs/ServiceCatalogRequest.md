
# ServiceCatalogRequest


## Properties

Name | Type
------------ | -------------
`name` | string
`category` | string
`price` | number
`description` | string

## Example

```typescript
import type { ServiceCatalogRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "name": null,
  "category": null,
  "price": null,
  "description": null,
} satisfies ServiceCatalogRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ServiceCatalogRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


