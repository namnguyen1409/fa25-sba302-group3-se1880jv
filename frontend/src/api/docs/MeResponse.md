
# MeResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`username` | string
`email` | string
`phone` | string
`active` | boolean
`locked` | boolean
`mfaEnabled` | boolean
`userProfile` | [SimpleProfileResponse](SimpleProfileResponse.md)
`firstLogin` | boolean
`roles` | [Set&lt;RoleNameResponse&gt;](RoleNameResponse.md)
`device` | [DeviceInfo](DeviceInfo.md)

## Example

```typescript
import type { MeResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "username": null,
  "email": null,
  "phone": null,
  "active": null,
  "locked": null,
  "mfaEnabled": null,
  "userProfile": null,
  "firstLogin": null,
  "roles": null,
  "device": null,
} satisfies MeResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as MeResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


