
# AppointmentResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`patient` | [PatientResponse](PatientResponse.md)
`specialty` | [SpecialtyResponse](SpecialtyResponse.md)
`type` | string
`status` | string
`source` | string
`note` | string
`queueTicket` | [QueueTicketResponse](QueueTicketResponse.md)

## Example

```typescript
import type { AppointmentResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "patient": null,
  "specialty": null,
  "type": null,
  "status": null,
  "source": null,
  "note": null,
  "queueTicket": null,
} satisfies AppointmentResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as AppointmentResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


