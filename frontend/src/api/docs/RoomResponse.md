
# RoomResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`name` | string
`roomType` | string
`floorNumber` | number
`capacity` | number
`description` | string
`department` | [DepartmentSimpleResponse](DepartmentSimpleResponse.md)

## Example

```typescript
import type { RoomResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "name": null,
  "roomType": null,
  "floorNumber": null,
  "capacity": null,
  "description": null,
  "department": null,
} satisfies RoomResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as RoomResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


