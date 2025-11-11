
# RoomRequest


## Properties

Name | Type
------------ | -------------
`name` | string
`roomType` | string
`floorNumber` | number
`capacity` | number
`description` | string
`departmentId` | string

## Example

```typescript
import type { RoomRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "name": null,
  "roomType": null,
  "floorNumber": null,
  "capacity": null,
  "description": null,
  "departmentId": null,
} satisfies RoomRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as RoomRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


