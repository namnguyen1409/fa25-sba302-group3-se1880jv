
# ServiceCatalogResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`code` | string
`name` | string
`category` | string
`price` | number
`description` | string

## Example

```typescript
import type { ServiceCatalogResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "code": null,
  "name": null,
  "category": null,
  "price": null,
  "description": null,
} satisfies ServiceCatalogResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ServiceCatalogResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


