
# DispenseRecordRequest


## Properties

Name | Type
------------ | -------------
`prescriptionId` | string
`dispensedById` | string
`status` | string
`dispensedAt` | Date
`totalCost` | number
`note` | string

## Example

```typescript
import type { DispenseRecordRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "prescriptionId": null,
  "dispensedById": null,
  "status": null,
  "dispensedAt": null,
  "totalCost": null,
  "note": null,
} satisfies DispenseRecordRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as DispenseRecordRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


