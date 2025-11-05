
# InvoiceRequest


## Properties

Name | Type
------------ | -------------
`patientId` | string
`examinationId` | string
`invoiceNumber` | string
`issueDate` | Date
`totalAmount` | number
`paid` | boolean
`note` | string

## Example

```typescript
import type { InvoiceRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "patientId": null,
  "examinationId": null,
  "invoiceNumber": null,
  "issueDate": null,
  "totalAmount": null,
  "paid": null,
  "note": null,
} satisfies InvoiceRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as InvoiceRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


