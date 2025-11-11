
# PatientResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`patientCode` | string
`fullName` | string
`dateOfBirth` | Date
`gender` | string
`bloodType` | string
`status` | string
`phone` | string
`email` | string
`address` | string
`insuranceNumber` | string
`initPassword` | string

## Example

```typescript
import type { PatientResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "patientCode": null,
  "fullName": null,
  "dateOfBirth": null,
  "gender": null,
  "bloodType": null,
  "status": null,
  "phone": null,
  "email": null,
  "address": null,
  "insuranceNumber": null,
  "initPassword": null,
} satisfies PatientResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PatientResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


