
# InvoiceResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`patient` | [PatientResponse](PatientResponse.md)
`examination` | [ExaminationResponse](ExaminationResponse.md)
`invoiceNumber` | string
`issueDate` | Date
`totalAmount` | number
`paid` | boolean
`note` | string
`items` | [Set&lt;InvoiceItemResponse&gt;](InvoiceItemResponse.md)
`room` | [RoomResponse](RoomResponse.md)
`assignedStaff` | [StaffResponse](StaffResponse.md)

## Example

```typescript
import type { InvoiceResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "patient": null,
  "examination": null,
  "invoiceNumber": null,
  "issueDate": null,
  "totalAmount": null,
  "paid": null,
  "note": null,
  "items": null,
  "room": null,
  "assignedStaff": null,
} satisfies InvoiceResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as InvoiceResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


