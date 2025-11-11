
# CustomApiResponseStaffResponse


## Properties

Name | Type
------------ | -------------
`code` | number
`message` | string
`data` | [StaffResponse](StaffResponse.md)
`timestamp` | Date
`path` | string

## Example

```typescript
import type { CustomApiResponseStaffResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "code": null,
  "message": null,
  "data": null,
  "timestamp": null,
  "path": null,
} satisfies CustomApiResponseStaffResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as CustomApiResponseStaffResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


