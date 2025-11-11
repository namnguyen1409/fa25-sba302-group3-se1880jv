
# AppointmentRequest


## Properties

Name | Type
------------ | -------------
`patientId` | string
`specialtyId` | string
`type` | string
`status` | string
`source` | string
`note` | string

## Example

```typescript
import type { AppointmentRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "patientId": null,
  "specialtyId": null,
  "type": null,
  "status": null,
  "source": null,
  "note": null,
} satisfies AppointmentRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as AppointmentRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


