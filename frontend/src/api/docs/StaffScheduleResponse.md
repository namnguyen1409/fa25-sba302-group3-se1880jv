
# StaffScheduleResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`dayOfWeek` | string
`startTime` | [LocalTime](LocalTime.md)
`endTime` | [LocalTime](LocalTime.md)
`available` | boolean
`staff` | [StaffResponse](StaffResponse.md)
`room` | [RoomResponse](RoomResponse.md)

## Example

```typescript
import type { StaffScheduleResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "dayOfWeek": null,
  "startTime": null,
  "endTime": null,
  "available": null,
  "staff": null,
  "room": null,
} satisfies StaffScheduleResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as StaffScheduleResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


