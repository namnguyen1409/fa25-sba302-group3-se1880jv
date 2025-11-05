
# ServiceOrderItemResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`service` | [ServiceCatalogResponse](ServiceCatalogResponse.md)
`price` | number
`note` | string

## Example

```typescript
import type { ServiceOrderItemResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "service": null,
  "price": null,
  "note": null,
} satisfies ServiceOrderItemResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ServiceOrderItemResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


