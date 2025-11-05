
# QueueTicketResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`appointmentId` | string
`assignedDoctor` | [StaffResponse](StaffResponse.md)
`assignedRoom` | [RoomResponse](RoomResponse.md)
`queueNumber` | string
`status` | string
`priority` | string
`examinationId` | string

## Example

```typescript
import type { QueueTicketResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "appointmentId": null,
  "assignedDoctor": null,
  "assignedRoom": null,
  "queueNumber": null,
  "status": null,
  "priority": null,
  "examinationId": null,
} satisfies QueueTicketResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as QueueTicketResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


