
# PatientRequest


## Properties

Name | Type
------------ | -------------
`fullName` | string
`dateOfBirth` | Date
`gender` | string
`bloodType` | string
`status` | string
`phone` | string
`email` | string
`address` | string
`insuranceNumber` | string

## Example

```typescript
import type { PatientRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "fullName": null,
  "dateOfBirth": null,
  "gender": null,
  "bloodType": null,
  "status": null,
  "phone": null,
  "email": null,
  "address": null,
  "insuranceNumber": null,
} satisfies PatientRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PatientRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


