
# OAuthAccountResponse


## Properties

Name | Type
------------ | -------------
`createdDate` | Date
`provider` | string
`email` | string
`name` | string
`avatarUrl` | string
`isRevoke` | boolean

## Example

```typescript
import type { OAuthAccountResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "createdDate": null,
  "provider": null,
  "email": null,
  "name": null,
  "avatarUrl": null,
  "isRevoke": null,
} satisfies OAuthAccountResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as OAuthAccountResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


