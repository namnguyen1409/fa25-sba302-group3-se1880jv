
# InvoiceItemResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`description` | string
`quantity` | number
`unitPrice` | number
`totalPrice` | number

## Example

```typescript
import type { InvoiceItemResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "description": null,
  "quantity": null,
  "unitPrice": null,
  "totalPrice": null,
} satisfies InvoiceItemResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as InvoiceItemResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


