
# TodaySummaryResponse


## Properties

Name | Type
------------ | -------------
`totalPatients` | number
`completedExams` | number
`waitingPatients` | number
`inService` | number
`invoices` | number
`totalRevenue` | number
`labOrders` | number
`imagingOrders` | number
`avgWaitingMinutes` | number

## Example

```typescript
import type { TodaySummaryResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "totalPatients": null,
  "completedExams": null,
  "waitingPatients": null,
  "inService": null,
  "invoices": null,
  "totalRevenue": null,
  "labOrders": null,
  "imagingOrders": null,
  "avgWaitingMinutes": null,
} satisfies TodaySummaryResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as TodaySummaryResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


