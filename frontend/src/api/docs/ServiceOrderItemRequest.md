
# ServiceOrderItemRequest


## Properties

Name | Type
------------ | -------------
`serviceOrderId` | string
`serviceId` | string
`price` | number
`note` | string

## Example

```typescript
import type { ServiceOrderItemRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "serviceOrderId": null,
  "serviceId": null,
  "price": null,
  "note": null,
} satisfies ServiceOrderItemRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ServiceOrderItemRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


