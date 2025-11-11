
# DeviceSessionResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`createdBy` | string
`deviceName` | string
`ipAddress` | string
`userAgent` | string
`trusted` | boolean
`expiresIn` | Date
`revoked` | boolean
`rememberMe` | boolean
`lastLoginAt` | Date

## Example

```typescript
import type { DeviceSessionResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "createdBy": null,
  "deviceName": null,
  "ipAddress": null,
  "userAgent": null,
  "trusted": null,
  "expiresIn": null,
  "revoked": null,
  "rememberMe": null,
  "lastLoginAt": null,
} satisfies DeviceSessionResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as DeviceSessionResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


