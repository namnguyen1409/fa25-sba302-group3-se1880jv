
# ServiceOrderResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`orderCode` | string
`items` | [Set&lt;ServiceOrderItemResponse&gt;](ServiceOrderItemResponse.md)
`room` | [RoomResponse](RoomResponse.md)
`assignedStaff` | [StaffResponse](StaffResponse.md)
`status` | string

## Example

```typescript
import type { ServiceOrderResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "orderCode": null,
  "items": null,
  "room": null,
  "assignedStaff": null,
  "status": null,
} satisfies ServiceOrderResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ServiceOrderResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


