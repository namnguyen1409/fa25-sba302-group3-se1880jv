
# AccountSettingResponse


## Properties

Name | Type
------------ | -------------
`username` | string
`email` | string
`active` | boolean
`mfaEnabled` | boolean
`firstLogin` | boolean
`oAuthAccounts` | [Set&lt;OAuthAccountResponse&gt;](OAuthAccountResponse.md)

## Example

```typescript
import type { AccountSettingResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "username": null,
  "email": null,
  "active": null,
  "mfaEnabled": null,
  "firstLogin": null,
  "oAuthAccounts": null,
} satisfies AccountSettingResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as AccountSettingResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


